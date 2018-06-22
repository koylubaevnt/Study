import { Component, OnInit } from '@angular/core';

import { FormBuilder, FormGroup, Validators, AbstractControl, FormControl } from '@angular/forms';

@Component({
  selector: 'app-demo-form-with-custom-validation',
  templateUrl: './demo-form-with-custom-validation.component.html',
  styleUrls: ['./demo-form-with-custom-validation.component.css']
})
export class DemoFormWithCustomValidationComponent implements OnInit {

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
