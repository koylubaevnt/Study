import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StylingDemoComponent } from './styling-demo.component';

describe('StylingDemoComponent', () => {
  let component: StylingDemoComponent;
  let fixture: ComponentFixture<StylingDemoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StylingDemoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StylingDemoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
