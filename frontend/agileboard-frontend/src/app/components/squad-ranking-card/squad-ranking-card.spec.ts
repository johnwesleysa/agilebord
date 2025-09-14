import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SquadRankingCard } from './squad-ranking-card';

describe('SquadRankingCard', () => {
  let component: SquadRankingCard;
  let fixture: ComponentFixture<SquadRankingCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SquadRankingCard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SquadRankingCard);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
