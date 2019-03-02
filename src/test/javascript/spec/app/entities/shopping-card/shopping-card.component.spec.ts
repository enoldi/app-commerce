/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppcommerceTestModule } from '../../../test.module';
import { ShoppingCardComponent } from 'app/entities/shopping-card/shopping-card.component';
import { ShoppingCardService } from 'app/entities/shopping-card/shopping-card.service';
import { ShoppingCard } from 'app/shared/model/shopping-card.model';

describe('Component Tests', () => {
    describe('ShoppingCard Management Component', () => {
        let comp: ShoppingCardComponent;
        let fixture: ComponentFixture<ShoppingCardComponent>;
        let service: ShoppingCardService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AppcommerceTestModule],
                declarations: [ShoppingCardComponent],
                providers: []
            })
                .overrideTemplate(ShoppingCardComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ShoppingCardComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ShoppingCardService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ShoppingCard(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.shoppingCards[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
