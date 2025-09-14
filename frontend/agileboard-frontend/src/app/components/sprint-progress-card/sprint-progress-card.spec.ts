import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SprintProgressCard } from './sprint-progress-card';

describe('SprintProgressCard', () => {
  let component: SprintProgressCard;
  let fixture: ComponentFixture<SprintProgressCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SprintProgressCard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SprintProgressCard);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
