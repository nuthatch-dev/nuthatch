import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeveloperRepresentativeComponent } from './developer-representative.component';

describe('DeveloperRepresentativeComponent', () => {
  let component: DeveloperRepresentativeComponent;
  let fixture: ComponentFixture<DeveloperRepresentativeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DeveloperRepresentativeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeveloperRepresentativeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
