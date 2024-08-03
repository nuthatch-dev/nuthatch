import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GwjCreateComponent } from './gwj-create.component';

describe('GwjCreateComponent', () => {
  let component: GwjCreateComponent;
  let fixture: ComponentFixture<GwjCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GwjCreateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GwjCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
