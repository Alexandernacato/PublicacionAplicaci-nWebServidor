import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Catalogo } from './catalogo/catalogo';
import { FormsModule } from '@angular/forms';
import { CatologoC } from './catologo-c/catologo-c';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Catalogo,CatologoC],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected title = 'Catalogo';
}
