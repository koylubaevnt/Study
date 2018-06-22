import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DiffersDemoComponent } from './differs-demo.component';

describe('DiffersDemoComponent', () => {
  let component: DiffersDemoComponent;
  let fixture: ComponentFixture<DiffersDemoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DiffersDemoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DiffersDemoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
