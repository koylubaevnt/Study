import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-all-hooks-demo',
  templateUrl: './all-hooks-demo.component.html',
  styleUrls: []
})
export class AllHooksDemoComponent implements OnInit {

  displayAfters: boolean;

  constructor() { }

  ngOnInit() {
    this.displayAfters = true;
  }

  toggleAfters(): void {
    this.displayAfters = !this.displayAfters;
  }

}
