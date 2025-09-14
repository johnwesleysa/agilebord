import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SprisquadRankingCard } from './sprisquad-ranking-card';

describe('SprisquadRankingCard', () => {
  let component: SprisquadRankingCard;
  let fixture: ComponentFixture<SprisquadRankingCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SprisquadRankingCard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SprisquadRankingCard);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
