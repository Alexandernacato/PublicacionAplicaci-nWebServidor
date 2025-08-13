import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
export interface Categoria {
  id?: number;
  nombre: string;
  descripcion: string;
  fechaCreacion: string;
}


@Component({
  selector: 'app-categories',
  imports: [CommonModule, FormsModule],
  templateUrl: './categories.html',
  styleUrl: './categories.css'
})
export class Categories {
  @Input() categories: Categoria[] = [];
  @Input() searchTerm = '';

  @Output() onNuevo = new EventEmitter<void>();
  @Output() onEditar = new EventEmitter<Categoria>();
  @Output() onEliminar = new EventEmitter<Categoria>();

  filteredCategories() {
    return this.categories.filter(c =>
      c.nombre.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      c.descripcion.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  abrirNuevo() {
    this.onNuevo.emit();
  }

  editarCategoria(cat: Categoria) {
    this.onEditar.emit(cat);
  }

  eliminarCategoria(cat: Categoria) {
    this.onEliminar.emit(cat);
  }
}
