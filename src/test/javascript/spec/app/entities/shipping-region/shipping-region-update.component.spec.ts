/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AppcommerceTestModule } from '../../../test.module';
import { ShippingRegionUpdateComponent } from 'app/entities/shipping-region/shipping-region-update.component';
import { ShippingRegionService } from 'app/entities/shipping-region/shipping-region.service';
import { ShippingRegion } from 'app/shared/model/shipping-region.model';

describe('Component Tests', () => {
    describe('ShippingRegion Management Update Component', () => {
        let comp: ShippingRegionUpdateComponent;
        let fixture: ComponentFixture<ShippingRegionUpdateComponent>;
        let service: ShippingRegionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AppcommerceTestModule],
                declarations: [ShippingRegionUpdateComponent]
            })
                .overrideTemplate(ShippingRegionUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ShippingRegionUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ShippingRegionService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ShippingRegion(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.shippingRegion = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ShippingRegion();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.shippingRegion = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
