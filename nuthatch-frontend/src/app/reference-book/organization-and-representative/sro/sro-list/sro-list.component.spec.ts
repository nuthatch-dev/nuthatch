import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SroListComponent } from './sro-list.component';

describe('SroListComponent', () => {
  let component: SroListComponent;
  let fixture: ComponentFixture<SroListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SroListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SroListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
