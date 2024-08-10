import { TestBed } from '@angular/core/testing';

import { IndividualEntrepreneurService } from './individual-entrepreneur.service';

describe('IndividualEntrepreneurService', () => {
  let service: IndividualEntrepreneurService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(IndividualEntrepreneurService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
