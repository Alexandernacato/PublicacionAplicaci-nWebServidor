import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Product } from '../Services/productsservice';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-products',
  imports: [CommonModule, FormsModule],
  standalone: true,
  templateUrl: './products.html',
  styleUrl: './products.css'
})
export class Products {
  @Input() products: Product[] = [];
  @Input() searchTerm = '';
   @Input() categoryMap: Map<number,string> = new Map();

  @Output() onNuevo = new EventEmitter<void>();
  @Output() onEditar = new EventEmitter<Product>();
  @Output() onEliminar = new EventEmitter<Product>();

  filteredProducts() {
    return this.products.filter(p =>
      p.name.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      p.description.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  abrirNuevo() {
    this.onNuevo.emit();
  }

  editarProducto(p: Product) {
    this.onEditar.emit(p);
  }

  eliminarProducto(p: Product) {
    this.onEliminar.emit(p);
  }
}
