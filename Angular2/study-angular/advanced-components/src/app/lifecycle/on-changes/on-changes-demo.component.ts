import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-on-changes-demo',
  templateUrl: './on-changes-demo.component.html',
  styleUrls: []
})
export class OnChangesDemoComponent implements OnInit {

  display: boolean;
  name: string;
  comment: string;

  constructor() { }

  ngOnInit(): void {
    this.display = true;
    this.name = 'Felipe';
    this.comment = 'I am learning so much';
  }

  setValues(nameField, commentField): void {
    this.name = nameField.value;
    this.comment = commentField.value;
  }

  toggle(): void {
    this.display = !this.display;
  }
}
