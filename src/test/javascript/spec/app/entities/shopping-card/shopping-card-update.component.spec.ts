/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AppcommerceTestModule } from '../../../test.module';
import { ShoppingCardUpdateComponent } from 'app/entities/shopping-card/shopping-card-update.component';
import { ShoppingCardService } from 'app/entities/shopping-card/shopping-card.service';
import { ShoppingCard } from 'app/shared/model/shopping-card.model';

describe('Component Tests', () => {
    describe('ShoppingCard Management Update Component', () => {
        let comp: ShoppingCardUpdateComponent;
        let fixture: ComponentFixture<ShoppingCardUpdateComponent>;
        let service: ShoppingCardService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AppcommerceTestModule],
                declarations: [ShoppingCardUpdateComponent]
            })
                .overrideTemplate(ShoppingCardUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ShoppingCardUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ShoppingCardService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ShoppingCard(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.shoppingCard = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ShoppingCard();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.shoppingCard = entity;
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
