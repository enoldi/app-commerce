/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppcommerceTestModule } from '../../../test.module';
import { ShippingRegionComponent } from 'app/entities/shipping-region/shipping-region.component';
import { ShippingRegionService } from 'app/entities/shipping-region/shipping-region.service';
import { ShippingRegion } from 'app/shared/model/shipping-region.model';

describe('Component Tests', () => {
    describe('ShippingRegion Management Component', () => {
        let comp: ShippingRegionComponent;
        let fixture: ComponentFixture<ShippingRegionComponent>;
        let service: ShippingRegionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AppcommerceTestModule],
                declarations: [ShippingRegionComponent],
                providers: []
            })
                .overrideTemplate(ShippingRegionComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ShippingRegionComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ShippingRegionService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ShippingRegion(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.shippingRegions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
