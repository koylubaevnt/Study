import { Component, OnInit, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-no-encapsulation',
  template: `
  <h4 class="ui horizontal divider header">
    No encapsulation example
  </h4>
  
  <div class="hightlight">
    This component uses <code>ViewEncapsulation.None</code>
  </div>
  `,
  styles: [`
  .hightlight {
    border: 2px dashed red;
    text-align: center;
    margin-bottom: 20px;
  }`],
  encapsulation: ViewEncapsulation.None
})
export class NoEncapsulationComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
