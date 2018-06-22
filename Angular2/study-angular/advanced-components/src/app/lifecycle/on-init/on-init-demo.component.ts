import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-on-init-demo',
  templateUrl: './on-init-demo.component.html'
})
export class OnInitDemoComponent implements OnInit {

  display: boolean;

  constructor() { 
    this.display = true;
  }

toggle(): void {
  this.display = !this.display;
}

  ngOnInit() {
  }

}
