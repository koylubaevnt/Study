import { Component, OnInit, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-native-encapsulation',
  template: `
  <h4 class="ui horizontal divider header">
    Native encapsulation example
  </h4>
  
  <div class="hightlight">
    This component uses <code>ViewEncapsulation.Native</code>
  </div>
  `,
  styles: [`
  .hightlight {
    border: 2px solid black;
    text-align: center;
    border-radius: 2px;
    margin-bottom: 20px;
  }`],
  encapsulation: ViewEncapsulation.Native
})
export class NativeEncapsulationComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
