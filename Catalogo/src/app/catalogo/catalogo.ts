import { Component, OnInit } from '@angular/core';
import { Product, ProductService } from '../Services/productsservice';
import { Categoria, CategoriaService } from '../Services/categoriaservices';
import { Products } from "../products/products";
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { Nav } from '../nav/nav';

@Component({
  selector: 'app-catalogo',
  imports: [Products,CommonModule, FormsModule, RouterModule, Nav],
  templateUrl: './catalogo.html',
  styleUrl: './catalogo.css'
})
export class Catalogo implements OnInit {
 products: Product[] = [];
  categories: Categoria[] = []; // array para categorías
  searchTerm = '';
  mostrarModal = false;
  productoSeleccionado: Product = this.obtenerProductoVacio();

  constructor(
    private productService: ProductService,
    private categoriaService: CategoriaService
  ) {}

  ngOnInit() {
    this.cargarCategorias();
    this.cargarProductos();
  }

  cargarCategorias() {
    this.categoriaService.getAll().subscribe({
      next: data => this.categories = data,
      error: err => console.error('Error cargando categorías', err)
    });
  }

  cargarProductos() {
    this.productService.getAll().subscribe({
      next: data => this.products = data,
      error: err => console.error('Error cargando productos', err)
    });
  }

  abrirModalParaNuevo() {
    this.productoSeleccionado = this.obtenerProductoVacio();
    this.mostrarModal = true;
  }

  abrirModalParaEditar(producto: Product) {
    this.productoSeleccionado = { ...producto };
    this.mostrarModal = true;
  }

  cerrarModal() {
    this.mostrarModal = false;
  }

  guardarProducto() {
    if (this.productoSeleccionado.id) {
      this.productService.update(this.productoSeleccionado.id, this.productoSeleccionado).subscribe({
        next: () => {
          this.cargarProductos();
          this.cerrarModal();
        },
        error: err => console.error('Error actualizando', err)
      });
    } else {
      this.productService.create(this.productoSeleccionado).subscribe({
        next: () => {
          this.cargarProductos();
          this.cerrarModal();
        },
        error: err => console.error('Error creando', err)
      });
    }
  }

  eliminarProducto(producto: Product) {
    if (producto.id && confirm(`¿Eliminar "${producto.name}"?`)) {
      this.productService.delete(producto.id).subscribe({
        next: () => this.cargarProductos(),
        error: err => console.error('Error eliminando', err)
      });
    }
  }

  obtenerProductoVacio(): Product {
    return { name: '', description: '', price: 0, categoryid: 0 };
  }

}
