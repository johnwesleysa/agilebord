import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SprintCard } from './sprint-card';

describe('SprintCard', () => {
  let component: SprintCard;
  let fixture: ComponentFixture<SprintCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SprintCard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SprintCard);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
