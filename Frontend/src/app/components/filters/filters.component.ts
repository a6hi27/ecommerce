import { Component } from '@angular/core';

@Component({
  selector: 'eca-filters',
  templateUrl: './filters.component.html',
  styleUrls: ['./filters.component.css']
})
export class FiltersComponent {
  selectedRating: number = 1;
  selectedPrice: number = 500;
  selectedBrands: { [brand: string]: boolean } = {};
  brands: string[] = ['Brand1', 'Brand2', 'Brand3']; // Add your brands here
}


