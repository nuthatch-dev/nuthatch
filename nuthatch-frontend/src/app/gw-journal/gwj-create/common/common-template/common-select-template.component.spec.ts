import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommonSelectTemplateComponent } from './common-select-template.component';

describe('CommonTemplateComponent', () => {
  let component: CommonSelectTemplateComponent;
  let fixture: ComponentFixture<CommonSelectTemplateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CommonSelectTemplateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CommonSelectTemplateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
