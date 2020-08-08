package be.vdab.FrituurFrida.domain;

import java.util.Date;

public class Gastenboek {

        private final long id;
        private final String name;
        private final String bericht;
        private Date date;

        public Gastenboek(long id, String name, String bericht, Date date) {
            this.id = id;
            this.name = name;
            this.bericht = bericht;
            this.date = date;
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getBericht() {
            return bericht;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
}
