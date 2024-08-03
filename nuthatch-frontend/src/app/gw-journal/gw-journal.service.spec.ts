import { TestBed } from '@angular/core/testing';

import { GwJournalService } from './gw-journal.service';

describe('GwJournalService', () => {
  let service: GwJournalService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GwJournalService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
