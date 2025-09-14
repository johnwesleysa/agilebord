import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivityFeedCard } from './activity-feed-card';

describe('ActivityFeedCard', () => {
  let component: ActivityFeedCard;
  let fixture: ComponentFixture<ActivityFeedCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ActivityFeedCard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActivityFeedCard);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
