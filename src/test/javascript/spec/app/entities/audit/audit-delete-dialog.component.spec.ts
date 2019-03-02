/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AppcommerceTestModule } from '../../../test.module';
import { AuditDeleteDialogComponent } from 'app/entities/audit/audit-delete-dialog.component';
import { AuditService } from 'app/entities/audit/audit.service';

describe('Component Tests', () => {
    describe('Audit Management Delete Component', () => {
        let comp: AuditDeleteDialogComponent;
        let fixture: ComponentFixture<AuditDeleteDialogComponent>;
        let service: AuditService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AppcommerceTestModule],
                declarations: [AuditDeleteDialogComponent]
            })
                .overrideTemplate(AuditDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AuditDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AuditService);
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
