import { Component, OnInit, EventEmitter } from '@angular/core';

import { FormBuilder, FormGroup, Validators, AbstractControl, FormControl } from '@angular/forms';

@Component({
  selector: 'app-demo-form-with-events',
  templateUrl: './demo-form-with-events.component.html',
  styleUrls: ['./demo-form-with-events.component.css']
})
export class DemoFormWithEventsComponent implements OnInit {

  myForm: FormGroup;
  sku: AbstractControl;

  constructor(fb: FormBuilder) {
    this.myForm = fb.group({
      'sku' : ['', Validators.compose([
        Validators.required, skuValidator
      ])],
      'sku2' : ['', Validators.required]
    });

    this.sku = this.myForm.controls['sku'];

    this.sku.valueChanges.subscribe((value: string) => {
      console.log('sku changed to:', value);
    })

    this.myForm.valueChanges.subscribe((form: any) => {
      console.log('form changed to:', form);
    });
  }

  ngOnInit() {
  }

  onSubmit(value: string):void {
    console.log('you submitted value:', value);
  }

}

function skuValidator(control: FormControl): { [s: string] : boolean } {
  if(!control.value.match(/^123/)) {
    return {invalidSku: true};
  }
}
