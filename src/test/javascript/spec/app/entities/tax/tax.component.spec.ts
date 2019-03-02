/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppcommerceTestModule } from '../../../test.module';
import { TaxComponent } from 'app/entities/tax/tax.component';
import { TaxService } from 'app/entities/tax/tax.service';
import { Tax } from 'app/shared/model/tax.model';

describe('Component Tests', () => {
    describe('Tax Management Component', () => {
        let comp: TaxComponent;
        let fixture: ComponentFixture<TaxComponent>;
        let service: TaxService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AppcommerceTestModule],
                declarations: [TaxComponent],
                providers: []
            })
                .overrideTemplate(TaxComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TaxComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TaxService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Tax(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.taxes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
