import { Component, OnInit, Input, HostBinding } from '@angular/core';

import { Product } from '../product.model';

@Component({
  selector: 'product-image',
  templateUrl: './product-image.component.html',
  /*`
  <img class="product-image" [src]="product.imageUrl">
  `
  */
  styleUrls: ['./product-image.component.css']
})
export class ProductImageComponent implements OnInit {

  @Input() product: Product;
  @HostBinding('attr.class') cssClass = 'ui small image';

  constructor() { }

  ngOnInit() {
  }

}
