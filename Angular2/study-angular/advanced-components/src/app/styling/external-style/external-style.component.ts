import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-external-style',
  template: `
  <h4 class="ui horizontal divider header">
    External style example
  </h4>
  
  <div class="hightlight">
    This uses component <code>styleUrls</code> property
  </div>
  `,
  styleUrls: ['./external-style.component.css']
})
export class ExternalStyleComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
