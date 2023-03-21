import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ToastMessegesComponent } from './toast-messeges.component';

describe('ToastMessegesComponent', () => {
  let component: ToastMessegesComponent;
  let fixture: ComponentFixture<ToastMessegesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ToastMessegesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ToastMessegesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
