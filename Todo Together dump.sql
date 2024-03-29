--
-- PostgreSQL database dump
--

-- Dumped from database version 13.6
-- Dumped by pg_dump version 14.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: list; Type: TABLE; Schema: public; Owner: fateen_sbd
--

CREATE TABLE public.list (
    listid integer NOT NULL,
    grouplistid integer NOT NULL,
    title character varying(50) NOT NULL,
    due date NOT NULL,
    ischecked boolean NOT NULL
);


ALTER TABLE public.list OWNER TO fateen_sbd;

--
-- Name: List_listid_seq; Type: SEQUENCE; Schema: public; Owner: fateen_sbd
--

CREATE SEQUENCE public."List_listid_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."List_listid_seq" OWNER TO fateen_sbd;

--
-- Name: List_listid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: fateen_sbd
--

ALTER SEQUENCE public."List_listid_seq" OWNED BY public.list.listid;


--
-- Name: grouplist; Type: TABLE; Schema: public; Owner: fateen_sbd
--

CREATE TABLE public.grouplist (
    grouplistid integer NOT NULL,
    groupname character varying(50) NOT NULL
);


ALTER TABLE public.grouplist OWNER TO fateen_sbd;

--
-- Name: grouplist_grouplistid_seq; Type: SEQUENCE; Schema: public; Owner: fateen_sbd
--

CREATE SEQUENCE public.grouplist_grouplistid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.grouplist_grouplistid_seq OWNER TO fateen_sbd;

--
-- Name: grouplist_grouplistid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: fateen_sbd
--

ALTER SEQUENCE public.grouplist_grouplistid_seq OWNED BY public.grouplist.grouplistid;


--
-- Name: grouplist_user; Type: TABLE; Schema: public; Owner: fateen_sbd
--

CREATE TABLE public.grouplist_user (
    userid integer NOT NULL,
    grouplistid integer NOT NULL
);


ALTER TABLE public.grouplist_user OWNER TO fateen_sbd;

--
-- Name: users; Type: TABLE; Schema: public; Owner: fateen_sbd
--

CREATE TABLE public.users (
    userid integer NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(100) NOT NULL
);


ALTER TABLE public.users OWNER TO fateen_sbd;

--
-- Name: user_userid_seq; Type: SEQUENCE; Schema: public; Owner: fateen_sbd
--

CREATE SEQUENCE public.user_userid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_userid_seq OWNER TO fateen_sbd;

--
-- Name: user_userid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: fateen_sbd
--

ALTER SEQUENCE public.user_userid_seq OWNED BY public.users.userid;


--
-- Name: grouplist grouplistid; Type: DEFAULT; Schema: public; Owner: fateen_sbd
--

ALTER TABLE ONLY public.grouplist ALTER COLUMN grouplistid SET DEFAULT nextval('public.grouplist_grouplistid_seq'::regclass);


--
-- Name: list listid; Type: DEFAULT; Schema: public; Owner: fateen_sbd
--

ALTER TABLE ONLY public.list ALTER COLUMN listid SET DEFAULT nextval('public."List_listid_seq"'::regclass);


--
-- Name: users userid; Type: DEFAULT; Schema: public; Owner: fateen_sbd
--

ALTER TABLE ONLY public.users ALTER COLUMN userid SET DEFAULT nextval('public.user_userid_seq'::regclass);


--
-- Data for Name: grouplist; Type: TABLE DATA; Schema: public; Owner: fateen_sbd
--

COPY public.grouplist (grouplistid, groupname) FROM stdin;
1	Tugas SBD
2	Tugas SBD
3	Tugas SBD
4	Tugas SBD
5	Tugas SBD
6	Tugas SBD
7	Tugas SBD
8	Tugas SBD
9	Tugas SBD
10	Tugas SBD
113	Tambah
114	KOTOR BANGED
115	KOTOR AJA
117	Makan Makan
16	Tugas Embun 2
118	Baru
119	Bikin
120	Bikin Baru
121	kok error
122	coba lagi
123	bener game ya
124	bismillah
46	Mantap
47	mantap
112	YEY BERSIH
\.


--
-- Data for Name: grouplist_user; Type: TABLE DATA; Schema: public; Owner: fateen_sbd
--

COPY public.grouplist_user (userid, grouplistid) FROM stdin;
25	112
25	113
25	114
25	115
27	16
28	1
28	1
25	47
25	46
25	1
36	1
25	117
25	118
25	119
36	120
36	121
36	122
36	123
36	124
28	46
28	47
\.


--
-- Data for Name: list; Type: TABLE DATA; Schema: public; Owner: fateen_sbd
--

COPY public.list (listid, grouplistid, title, due, ischecked) FROM stdin;
6	1	Tugas Jaya	2001-12-16	t
9	1	bismillah	2022-06-22	t
10	1	yuk bisa yuk	2022-06-23	t
5	1	Tugas EMbun	2001-12-16	f
11	1	Tugas Baru Selesai	2022-06-18	t
12	117	Main Beckel	2022-06-30	t
13	117	Main game	2022-07-12	t
14	118	APaya	2022-06-29	f
16	122	bismillah ga crash	2022-06-07	f
17	123	masih error keknya	2022-06-29	f
18	120	mari kita lihat	2022-06-17	f
19	124	no error fix	2022-06-27	f
15	119	Baru	2022-12-31	t
20	119	Masukin	2024-12-31	t
21	119	bisa dong	2022-12-30	f
8	1	Tugas Siapa	2001-12-06	t
7	1	Tugas Siapa	2001-12-16	t
3	1	Tugas Rumah	2001-12-10	t
4	1	Tugas Kemas	2001-12-22	t
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: fateen_sbd
--

COPY public.users (userid, username, password) FROM stdin;
27	Embun	$2a$10$f4Y/aeqAPfvWXzrtMYpRreThCt0OMER/CihwqrOfkRPc8mak7wwJG
28	Sabet	$2a$10$vrFLIr3asNrLB0KOIb6kMuzPzHhfxy0jCjOY/sgbMymqz6lb/dHjO
30	Enin	$2a$10$NsekeIthu6587p9SMOj6tOabc7CsCREBUHJXe3MwojqjtUcHsvIb6
32	Nada	$2a$10$xkXwQwDDW9kfWb2ixKu3qexfatnsH1SoZq3EZsx9HyQulxuIK56E2
33	Natthan	$2a$10$.0E8GN6.6E47KyFxWaQEDO4MMQ82dmx1UyAYHhSRg5UYaebzkTArG
34	Jaya	$2a$10$NprydjGQxzyO/hNeskIH2.LFXaPWhsFL8/oEOYdVB2zfd7JmnG/iO
36	Ghulam	$2a$10$kB9qvgGwHkbOaA0PwhpvaOIh8DC.3VzY0.ivjPtCW7vpceHrP1QFC
25	Fateen	$2a$10$/wBKVOBKkTNag3qBYOgQueZrsjezNzA6vWSZ/UMiBcpmTACB7TwU.
\.


--
-- Name: List_listid_seq; Type: SEQUENCE SET; Schema: public; Owner: fateen_sbd
--

SELECT pg_catalog.setval('public."List_listid_seq"', 21, true);


--
-- Name: grouplist_grouplistid_seq; Type: SEQUENCE SET; Schema: public; Owner: fateen_sbd
--

SELECT pg_catalog.setval('public.grouplist_grouplistid_seq', 124, true);


--
-- Name: user_userid_seq; Type: SEQUENCE SET; Schema: public; Owner: fateen_sbd
--

SELECT pg_catalog.setval('public.user_userid_seq', 36, true);


--
-- Name: list List_pkey; Type: CONSTRAINT; Schema: public; Owner: fateen_sbd
--

ALTER TABLE ONLY public.list
    ADD CONSTRAINT "List_pkey" PRIMARY KEY (listid);


--
-- Name: grouplist grouplist_pkey; Type: CONSTRAINT; Schema: public; Owner: fateen_sbd
--

ALTER TABLE ONLY public.grouplist
    ADD CONSTRAINT grouplist_pkey PRIMARY KEY (grouplistid);


--
-- Name: users uniqueUsername; Type: CONSTRAINT; Schema: public; Owner: fateen_sbd
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT "uniqueUsername" UNIQUE (username);


--
-- Name: users user_pkey; Type: CONSTRAINT; Schema: public; Owner: fateen_sbd
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT user_pkey PRIMARY KEY (userid);


--
-- Name: list List_grouplistid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: fateen_sbd
--

ALTER TABLE ONLY public.list
    ADD CONSTRAINT "List_grouplistid_fkey" FOREIGN KEY (grouplistid) REFERENCES public.grouplist(grouplistid) ON DELETE CASCADE NOT VALID;


--
-- Name: grouplist_user grouplist_user_grouplistid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: fateen_sbd
--

ALTER TABLE ONLY public.grouplist_user
    ADD CONSTRAINT grouplist_user_grouplistid_fkey FOREIGN KEY (grouplistid) REFERENCES public.grouplist(grouplistid) ON DELETE CASCADE NOT VALID;


--
-- Name: grouplist_user grouplist_user_userid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: fateen_sbd
--

ALTER TABLE ONLY public.grouplist_user
    ADD CONSTRAINT grouplist_user_userid_fkey FOREIGN KEY (userid) REFERENCES public.users(userid) ON DELETE CASCADE;


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: azure_pg_admin
--

REVOKE ALL ON SCHEMA public FROM azuresu;
REVOKE ALL ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO azure_pg_admin;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- Name: FUNCTION pg_replication_origin_advance(text, pg_lsn); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_replication_origin_advance(text, pg_lsn) TO azure_pg_admin;


--
-- Name: FUNCTION pg_replication_origin_create(text); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_replication_origin_create(text) TO azure_pg_admin;


--
-- Name: FUNCTION pg_replication_origin_drop(text); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_replication_origin_drop(text) TO azure_pg_admin;


--
-- Name: FUNCTION pg_replication_origin_oid(text); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_replication_origin_oid(text) TO azure_pg_admin;


--
-- Name: FUNCTION pg_replication_origin_progress(text, boolean); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_replication_origin_progress(text, boolean) TO azure_pg_admin;


--
-- Name: FUNCTION pg_replication_origin_session_is_setup(); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_replication_origin_session_is_setup() TO azure_pg_admin;


--
-- Name: FUNCTION pg_replication_origin_session_progress(boolean); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_replication_origin_session_progress(boolean) TO azure_pg_admin;


--
-- Name: FUNCTION pg_replication_origin_session_reset(); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_replication_origin_session_reset() TO azure_pg_admin;


--
-- Name: FUNCTION pg_replication_origin_session_setup(text); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_replication_origin_session_setup(text) TO azure_pg_admin;


--
-- Name: FUNCTION pg_replication_origin_xact_reset(); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_replication_origin_xact_reset() TO azure_pg_admin;


--
-- Name: FUNCTION pg_replication_origin_xact_setup(pg_lsn, timestamp with time zone); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_replication_origin_xact_setup(pg_lsn, timestamp with time zone) TO azure_pg_admin;


--
-- Name: FUNCTION pg_show_replication_origin_status(OUT local_id oid, OUT external_id text, OUT remote_lsn pg_lsn, OUT local_lsn pg_lsn); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_show_replication_origin_status(OUT local_id oid, OUT external_id text, OUT remote_lsn pg_lsn, OUT local_lsn pg_lsn) TO azure_pg_admin;


--
-- Name: FUNCTION pg_stat_reset(); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_stat_reset() TO azure_pg_admin;


--
-- Name: FUNCTION pg_stat_reset_shared(text); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_stat_reset_shared(text) TO azure_pg_admin;


--
-- Name: FUNCTION pg_stat_reset_single_function_counters(oid); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_stat_reset_single_function_counters(oid) TO azure_pg_admin;


--
-- Name: FUNCTION pg_stat_reset_single_table_counters(oid); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_stat_reset_single_table_counters(oid) TO azure_pg_admin;


--
-- Name: COLUMN pg_config.name; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(name) ON TABLE pg_catalog.pg_config TO azure_pg_admin;


--
-- Name: COLUMN pg_config.setting; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(setting) ON TABLE pg_catalog.pg_config TO azure_pg_admin;


--
-- Name: COLUMN pg_hba_file_rules.line_number; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(line_number) ON TABLE pg_catalog.pg_hba_file_rules TO azure_pg_admin;


--
-- Name: COLUMN pg_hba_file_rules.type; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(type) ON TABLE pg_catalog.pg_hba_file_rules TO azure_pg_admin;


--
-- Name: COLUMN pg_hba_file_rules.database; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(database) ON TABLE pg_catalog.pg_hba_file_rules TO azure_pg_admin;


--
-- Name: COLUMN pg_hba_file_rules.user_name; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(user_name) ON TABLE pg_catalog.pg_hba_file_rules TO azure_pg_admin;


--
-- Name: COLUMN pg_hba_file_rules.address; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(address) ON TABLE pg_catalog.pg_hba_file_rules TO azure_pg_admin;


--
-- Name: COLUMN pg_hba_file_rules.netmask; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(netmask) ON TABLE pg_catalog.pg_hba_file_rules TO azure_pg_admin;


--
-- Name: COLUMN pg_hba_file_rules.auth_method; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(auth_method) ON TABLE pg_catalog.pg_hba_file_rules TO azure_pg_admin;


--
-- Name: COLUMN pg_hba_file_rules.options; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(options) ON TABLE pg_catalog.pg_hba_file_rules TO azure_pg_admin;


--
-- Name: COLUMN pg_hba_file_rules.error; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(error) ON TABLE pg_catalog.pg_hba_file_rules TO azure_pg_admin;


--
-- Name: COLUMN pg_replication_origin_status.local_id; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(local_id) ON TABLE pg_catalog.pg_replication_origin_status TO azure_pg_admin;


--
-- Name: COLUMN pg_replication_origin_status.external_id; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(external_id) ON TABLE pg_catalog.pg_replication_origin_status TO azure_pg_admin;


--
-- Name: COLUMN pg_replication_origin_status.remote_lsn; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(remote_lsn) ON TABLE pg_catalog.pg_replication_origin_status TO azure_pg_admin;


--
-- Name: COLUMN pg_replication_origin_status.local_lsn; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(local_lsn) ON TABLE pg_catalog.pg_replication_origin_status TO azure_pg_admin;


--
-- Name: COLUMN pg_shmem_allocations.name; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(name) ON TABLE pg_catalog.pg_shmem_allocations TO azure_pg_admin;


--
-- Name: COLUMN pg_shmem_allocations.off; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(off) ON TABLE pg_catalog.pg_shmem_allocations TO azure_pg_admin;


--
-- Name: COLUMN pg_shmem_allocations.size; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(size) ON TABLE pg_catalog.pg_shmem_allocations TO azure_pg_admin;


--
-- Name: COLUMN pg_shmem_allocations.allocated_size; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(allocated_size) ON TABLE pg_catalog.pg_shmem_allocations TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.starelid; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(starelid) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.staattnum; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(staattnum) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stainherit; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stainherit) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stanullfrac; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stanullfrac) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stawidth; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stawidth) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stadistinct; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stadistinct) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stakind1; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stakind1) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stakind2; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stakind2) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stakind3; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stakind3) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stakind4; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stakind4) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stakind5; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stakind5) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.staop1; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(staop1) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.staop2; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(staop2) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.staop3; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(staop3) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.staop4; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(staop4) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.staop5; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(staop5) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stacoll1; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stacoll1) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stacoll2; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stacoll2) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stacoll3; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stacoll3) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stacoll4; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stacoll4) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stacoll5; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stacoll5) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stanumbers1; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stanumbers1) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stanumbers2; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stanumbers2) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stanumbers3; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stanumbers3) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stanumbers4; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stanumbers4) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stanumbers5; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stanumbers5) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stavalues1; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stavalues1) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stavalues2; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stavalues2) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stavalues3; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stavalues3) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stavalues4; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stavalues4) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stavalues5; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stavalues5) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_subscription.oid; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(oid) ON TABLE pg_catalog.pg_subscription TO azure_pg_admin;


--
-- Name: COLUMN pg_subscription.subdbid; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(subdbid) ON TABLE pg_catalog.pg_subscription TO azure_pg_admin;


--
-- Name: COLUMN pg_subscription.subname; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(subname) ON TABLE pg_catalog.pg_subscription TO azure_pg_admin;


--
-- Name: COLUMN pg_subscription.subowner; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(subowner) ON TABLE pg_catalog.pg_subscription TO azure_pg_admin;


--
-- Name: COLUMN pg_subscription.subenabled; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(subenabled) ON TABLE pg_catalog.pg_subscription TO azure_pg_admin;


--
-- Name: COLUMN pg_subscription.subconninfo; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(subconninfo) ON TABLE pg_catalog.pg_subscription TO azure_pg_admin;


--
-- Name: COLUMN pg_subscription.subslotname; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(subslotname) ON TABLE pg_catalog.pg_subscription TO azure_pg_admin;


--
-- Name: COLUMN pg_subscription.subsynccommit; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(subsynccommit) ON TABLE pg_catalog.pg_subscription TO azure_pg_admin;


--
-- Name: COLUMN pg_subscription.subpublications; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(subpublications) ON TABLE pg_catalog.pg_subscription TO azure_pg_admin;


--
-- PostgreSQL database dump complete
--

