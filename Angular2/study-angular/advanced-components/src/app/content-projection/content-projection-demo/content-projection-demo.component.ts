import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-content-projection-demo',
  template: `
  <div app-message header="MyMessage">
    This is the content of the message
  </div>
  `
})
export class ContentProjectionDemoComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
