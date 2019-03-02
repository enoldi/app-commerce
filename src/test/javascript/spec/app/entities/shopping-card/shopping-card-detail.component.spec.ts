/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppcommerceTestModule } from '../../../test.module';
import { ShoppingCardDetailComponent } from 'app/entities/shopping-card/shopping-card-detail.component';
import { ShoppingCard } from 'app/shared/model/shopping-card.model';

describe('Component Tests', () => {
    describe('ShoppingCard Management Detail Component', () => {
        let comp: ShoppingCardDetailComponent;
        let fixture: ComponentFixture<ShoppingCardDetailComponent>;
        const route = ({ data: of({ shoppingCard: new ShoppingCard(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AppcommerceTestModule],
                declarations: [ShoppingCardDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ShoppingCardDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ShoppingCardDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.shoppingCard).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
