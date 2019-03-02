/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppcommerceTestModule } from '../../../test.module';
import { ShippingRegionDetailComponent } from 'app/entities/shipping-region/shipping-region-detail.component';
import { ShippingRegion } from 'app/shared/model/shipping-region.model';

describe('Component Tests', () => {
    describe('ShippingRegion Management Detail Component', () => {
        let comp: ShippingRegionDetailComponent;
        let fixture: ComponentFixture<ShippingRegionDetailComponent>;
        const route = ({ data: of({ shippingRegion: new ShippingRegion(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AppcommerceTestModule],
                declarations: [ShippingRegionDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ShippingRegionDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ShippingRegionDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.shippingRegion).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
