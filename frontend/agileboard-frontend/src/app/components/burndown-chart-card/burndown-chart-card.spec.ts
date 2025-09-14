import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BurndownChartCard } from './burndown-chart-card';

describe('BurndownChartCard', () => {
  let component: BurndownChartCard;
  let fixture: ComponentFixture<BurndownChartCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BurndownChartCard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BurndownChartCard);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
