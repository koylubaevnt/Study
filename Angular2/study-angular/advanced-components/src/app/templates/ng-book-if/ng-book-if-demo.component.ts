import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-ng-book-if-demo',
  templateUrl: './ng-book-if-demo.component.html',
  styleUrls: []
})
export class NgBookIfDemoComponent implements OnInit {

  display: boolean;

  constructor() {
    this.display = true;
   }

  ngOnInit() {
  }

  toggle(): void {
    this.display = !this.display;
  }

}
