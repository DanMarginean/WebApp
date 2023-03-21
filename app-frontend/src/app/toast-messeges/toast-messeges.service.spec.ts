import { TestBed } from '@angular/core/testing';

import { ToastMessegesService } from './toast-messeges.service';

describe('ToastMessegesService', () => {
  let service: ToastMessegesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ToastMessegesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
