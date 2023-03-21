import { TestBed } from '@angular/core/testing';

import { UpdateDialogService } from './update-dialog.service';

describe('UpdateDialogService', () => {
  let service: UpdateDialogService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UpdateDialogService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
