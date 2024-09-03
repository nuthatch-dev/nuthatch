import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateCounterpartyComponent } from './create-counterparty.component';

describe('CreateCounterpartyComponent', () => {
  let component: CreateCounterpartyComponent;
  let fixture: ComponentFixture<CreateCounterpartyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateCounterpartyComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateCounterpartyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
