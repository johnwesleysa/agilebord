import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StatCart } from './stat-cart';

describe('StatCart', () => {
  let component: StatCart;
  let fixture: ComponentFixture<StatCart>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StatCart]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StatCart);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
