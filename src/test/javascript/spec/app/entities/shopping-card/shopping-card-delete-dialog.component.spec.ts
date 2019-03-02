/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AppcommerceTestModule } from '../../../test.module';
import { ShoppingCardDeleteDialogComponent } from 'app/entities/shopping-card/shopping-card-delete-dialog.component';
import { ShoppingCardService } from 'app/entities/shopping-card/shopping-card.service';

describe('Component Tests', () => {
    describe('ShoppingCard Management Delete Component', () => {
        let comp: ShoppingCardDeleteDialogComponent;
        let fixture: ComponentFixture<ShoppingCardDeleteDialogComponent>;
        let service: ShoppingCardService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AppcommerceTestModule],
                declarations: [ShoppingCardDeleteDialogComponent]
            })
                .overrideTemplate(ShoppingCardDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ShoppingCardDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ShoppingCardService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
