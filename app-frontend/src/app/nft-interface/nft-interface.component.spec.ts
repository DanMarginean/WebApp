import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NftInterfaceComponent } from './nft-interface.component';

describe('NftInterfaceComponent', () => {
  let component: NftInterfaceComponent;
  let fixture: ComponentFixture<NftInterfaceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NftInterfaceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NftInterfaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
