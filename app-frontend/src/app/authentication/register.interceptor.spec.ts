import { TestBed } from '@angular/core/testing';

import { RegisterInterceptor } from './register.interceptor';

describe('RegisterInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      RegisterInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: RegisterInterceptor = TestBed.inject(RegisterInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
