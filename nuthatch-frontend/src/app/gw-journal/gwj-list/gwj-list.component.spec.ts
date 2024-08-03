import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GwjListComponent } from './gwj-list.component';

describe('GwjListComponent', () => {
  let component: GwjListComponent;
  let fixture: ComponentFixture<GwjListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GwjListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GwjListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
