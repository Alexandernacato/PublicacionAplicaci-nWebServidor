import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CatologoC } from './catologo-c';

describe('CatologoC', () => {
  let component: CatologoC;
  let fixture: ComponentFixture<CatologoC>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CatologoC]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CatologoC);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
