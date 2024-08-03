import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GwjDetailsComponent } from './gwj-details.component';

describe('GwjDetailsComponent', () => {
  let component: GwjDetailsComponent;
  let fixture: ComponentFixture<GwjDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GwjDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GwjDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
