import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DemoFormWithCustomValidationComponent } from './demo-form-with-custom-validation.component';

describe('DemoFormWithCustomValidationComponent', () => {
  let component: DemoFormWithCustomValidationComponent;
  let fixture: ComponentFixture<DemoFormWithCustomValidationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DemoFormWithCustomValidationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DemoFormWithCustomValidationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
