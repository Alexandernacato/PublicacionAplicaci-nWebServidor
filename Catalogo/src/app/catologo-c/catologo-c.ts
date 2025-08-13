import { Component, OnInit } from '@angular/core';
import { CategoriaService, Categoria } from '../Services/categoriaservices';
import { Categories } from '../categories/categories';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { Nav } from '../nav/nav';

@Component({
  selector: 'app-catologo-c',
  imports: [Categories, CommonModule, FormsModule, RouterModule, Nav],
  templateUrl: './catologo-c.html',
  styleUrl: './catologo-c.css'
})
export class CatologoC implements OnInit{
  categorias: Categoria[] = [];
  searchTerm = '';
  mostrarModal = false;
  categoriaSeleccionada: Categoria = this.obtenerCategoriaVacia();

  constructor(private categoriaService: CategoriaService) {}

  ngOnInit() {
    this.cargarCategorias();
  }

  cargarCategorias() {
    this.categoriaService.getAll().subscribe({
      next: data => this.categorias = data,
      error: err => console.error('Error cargando categorías', err)
    });
  }

  abrirModalParaNuevo() {
    this.categoriaSeleccionada = this.obtenerCategoriaVacia();
    this.mostrarModal = true;
  }

  abrirModalParaEditar(cat: Categoria) {
    this.categoriaSeleccionada = { ...cat };
    this.mostrarModal = true;
  }

  cerrarModal() {
    this.mostrarModal = false;
  }

  guardarCategoria() {
    if (this.categoriaSeleccionada.id) {
      this.categoriaService.update(this.categoriaSeleccionada.id, this.categoriaSeleccionada)
        .subscribe({
          next: () => {
            this.cargarCategorias();
            this.cerrarModal();
          },
          error: err => console.error('Error actualizando', err)
        });
    } else {
      const nuevaCat = { ...this.categoriaSeleccionada };
      delete nuevaCat.id;
      this.categoriaService.create(nuevaCat)
        .subscribe({
          next: () => {
            this.cargarCategorias();
            this.cerrarModal();
          },
          error: err => console.error('Error creando', err)
        });
    }
  }

  eliminarCategoria(cat: Categoria) {
    if (cat.id && confirm(`¿Eliminar "${cat.nombre}"?`)) {
      this.categoriaService.delete(cat.id).subscribe({
        next: () => this.cargarCategorias(),
        error: err => console.error('Error eliminando', err)
      });
    }
  }

  obtenerCategoriaVacia(): Categoria {
    return {
      nombre: '',
      descripcion: '',
      fechaCreacion: ''
    };
  }
}
