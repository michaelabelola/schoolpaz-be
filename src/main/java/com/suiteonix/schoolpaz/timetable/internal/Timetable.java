package com.suiteonix.schoolpaz.timetable.internal;

import com.suiteonix.schoolpaz.department.internal.Auditable;
import com.suiteonix.schoolpaz.kernel.interfaces.DomainObject;
import com.suiteonix.schoolpaz.lecture.internal.Lecture;
import com.suiteonix.schoolpaz.timetable.TimetableId;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@Entity
@DomainObject
@RequiredArgsConstructor
@Table(name = "timetable")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Timetable extends Auditable<Timetable> {

    @EmbeddedId
    TimetableId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id", referencedColumnName = "id")
    Lecture lecture;

    @Column(name = "day_of_week")
    @Enumerated(EnumType.STRING)
    DayOfWeek dayOfWeek;

    @Column(name = "start_time")
    LocalTime startTime;

    @Column(name = "end_time")
    LocalTime endTime;

    @Column(name = "location")
    String location;

    @Column(name = "room_number")
    String roomNumber;

    @Column(name = "is_recurring")
    boolean isRecurring;

    @Column(name = "notes", columnDefinition = "TEXT")
    String notes;
}