import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CursoService, Curso } from '../../service/curso.service';

@Component({ selector: 'app-cursos', standalone: true, imports: [CommonModule, ReactiveFormsModule], templateUrl: './cursos.component.html', styleUrl: './cursos.component.css' })
export class CursosComponent implements OnInit {
  cursos: Curso[] = [];
  form: FormGroup;
  mensaje = '';
  constructor(private fb: FormBuilder, private servicio: CursoService) {
    this.form = this.fb.group({
      ciclo_lectivo: [new Date().getFullYear(), [Validators.required, Validators.min(2000)]],
      grado: ['', Validators.required],
      division: ['', Validators.required],
      turno: ['', Validators.required],
      cupo_maximo: [30, [Validators.required, Validators.min(1)]]
    });
  }
  ngOnInit(): void { this.cargar(); }
  invalido(campo: string): boolean {
    const c = this.form.get(campo);
    return !!c && c.invalid && c.touched;
  }
  cargar(): void {
    this.servicio.listarCursos().subscribe({ next: d => this.cursos = d, error: () => this.mensaje = 'No se pudo cargar la lista' });
  }
  guardar(): void {
    if (this.form.invalid) { this.form.markAllAsTouched(); return; }
    this.servicio.agregarCurso(this.form.value).subscribe({
      next: () => {
        this.form.reset({ ciclo_lectivo: new Date().getFullYear(), grado: '', division: '', turno: '', cupo_maximo: 30 });
        this.mensaje = '';
        this.cargar();
      },
      error: () => this.mensaje = 'No se pudo guardar'
    });
  }
}