import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectStatusCard } from './project-status-card';

describe('ProjectStatusCard', () => {
  let component: ProjectStatusCard;
  let fixture: ComponentFixture<ProjectStatusCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProjectStatusCard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProjectStatusCard);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
