import { TestBed } from '@angular/core/testing';

import { NftInterfaceService } from './nft-interface.service';

describe('NftInterfaceService', () => {
  let service: NftInterfaceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NftInterfaceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
